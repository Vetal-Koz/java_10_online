import {AfterViewInit, Component, HostListener, OnInit} from '@angular/core';
import { PdpData } from '../../models/pdp.data';
import { CarVariantData } from '../../models/car-variant-data';
import { CarEngineData } from '../../models/car-engine.data';
import { CarService } from '../../services/car.service';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { CarVariantList } from "./helpModels/car-variant-list";
import {carEngineList} from "./helpModels/car-engine-list";
import {UserService} from "../../services/user.service";
import {User} from "../../models/principal/user.data";
import {UserResponseData} from "../../models/user-response.data";
import {map} from "rxjs";



const defaultCarVariantList: CarVariantList = {
  color: [],
  year: [],
  kilometrage: [],
  transmission: [],
  drivetrain: [],
  price: [],
  numberOfDoors: [],
  numberOfSeats: [],
  safetyRating: [],
}

const defaultCarEngineList: carEngineList = {
  typeOfFuel: [],
  displacement: [],
  horsepower: [],
  fuelEconomy: [],
  torque: [],
  cylinders: [],
}


@Component({
  selector: 'app-pdp',
  templateUrl: './pdp.component.html',
  styleUrl: './pdp.component.css'
})

export class PdpComponent implements OnInit{
  cars?: PdpData;

  carVariants: CarVariantData[] = [];

  selectedCarVariant?: CarVariantData;

  currentVariants: CarVariantData[] = [];

  countOfSelectFunction: number = 0;

  isSmall?: boolean;

  innerWidth?: number;

  carPrice: string = '';

  userEmail = localStorage.getItem('email')

  user?: UserResponseData;

  carVariantList= defaultCarVariantList;

  carEngineList = defaultCarEngineList;

  selectedImage?: string;

  form: FormGroup = this.formBuilder.group({
    color: [null, Validators.required],
    year: [null, Validators.required],
    kilometrage: [null, Validators.required],
    transmission: [null, Validators.required],
    drivetrain: [null, Validators.required],
    numberOfDoors: [null, Validators.required],
    typeOfFuel: [null, Validators.required],
    displacement: [null, Validators.required],
    fuelEconomy: [null, Validators.required],
    horsepower: [null, Validators.required],
  })

  constructor(private carService: CarService, private route: ActivatedRoute,
              private formBuilder: FormBuilder, private userService: UserService,
              private router: Router) {}


  getInfoById(id: number){
    this.carService.findById(id)
    .subscribe(res => {
        this.cars = res,
        this.selectedImage = res.carImages[0],
        this.carVariants = res.carVariantResponses,
          this.currentVariants = this.carVariants;
        this.initCarVariantSets(res.carVariantResponses),
        this.initCarEngineSets(res.carVariantResponses)
    });

  }

  selectMainImage(image: string): void{
    this.selectedImage = image;
  }


  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'))
    this.getInfoById(id);
    this.innerWidth = window.innerWidth;
    this.checkCurrentWidth();

    this.route.queryParamMap
      .pipe(
        // @ts-ignore
        map(map => map['params'])
      )
      .subscribe(params => {
          if (params) {
            const typeOfFuel = params?.typeOfFuel;
            const transmission = params?.transmission;
            if (typeOfFuel) {
              this.form.controls['typeOfFuel'].setValue(typeOfFuel);
            }
            if (transmission) {
              this.form.controls['transmission'].setValue(transmission);
            }
          }
        }
      )
    if(this.userEmail !== null) {
      this.userService.loadCurrentUserData(this.userEmail).subscribe(res => this.user = res);
    }
  }

  addCarVariantToUser(){
    if (this.user && this.selectedCarVariant) {
      this.userService.attachCarVariantToUser(this.user.id, this.selectedCarVariant.id).subscribe(
        res => {
          this.router.navigate(['/wishList']);
      }
      );
    }
  }

  onClickForVariant(text: string){
    if (this.form.controls[text].value !== null) {
      if (text !== 'year' && text !== 'numberOfDoors') {
        // @ts-ignore
        this.currentVariants = this.currentVariants.filter(cv => cv[text] === this.form.controls[text].value);
      }
      else {
        this.currentVariants = this.currentVariants.filter(cv => cv[text] === Number(this.form.controls[text].value));
      }
      this.initCarVariantSets(this.currentVariants);
      this.initCarEngineSets(this.currentVariants);
    }
  }

  onClickForEngine(text: string){
    if (this.form.controls[text].value !== null) {
      if (text !== 'horsepower') {
        // @ts-ignore
        this.currentVariants = this.currentVariants.filter(cv => cv.carEngineResponse[text] === this.form.controls[text].value);
      }
      else {
        this.currentVariants = this.currentVariants.filter(cv => cv.carEngineResponse[text] === Number(this.form.controls[text].value));
      }
      this.initCarVariantSets(this.currentVariants);
      this.initCarEngineSets(this.currentVariants);
    }
  }

  @HostListener('window:resize', ['$event.target.innerWidth'])
  onResize(width: number) {
    console.log(width);
    this.innerWidth = width;
    this.checkCurrentWidth();
  }

  checkCurrentWidth(): void{
    // @ts-ignore
    if (this.innerWidth < 1060){
      this.isSmall = true;
    }else {
      this.isSmall = false;
    }
  }

  getCurrentCarVariant():boolean{
    if (!this.form.invalid) {
      if (this.currentVariants.length === 1) {
        this.selectedCarVariant = this.currentVariants[0];
        this.carPrice = this.selectedCarVariant.price;
      }
      return true;
    }
    else {
      return false
    }
  }

  initCarVariantSets(carVariants: CarVariantData[]): void{
    const colors = new Set<string>(carVariants.map(cv => cv.color));
    const years = new Set<number>(carVariants.map(cv => cv.year));
    const kilometrages = new Set<string>(carVariants.map(cv => cv.kilometrage));
    const transmissions = new Set<string>(carVariants.map(cv => cv.transmission));
    const drivetrains = new Set<string>(carVariants.map(cv => cv.drivetrain));
    const prices = new Set<string>(carVariants.map(cv => cv.price));
    const numbersOfDoors = new Set<number>(carVariants.map(cv => cv.numberOfDoors));
    const numbersOfSeats = new Set<number>(carVariants.map(cv => cv.numberOfSeats));
    const safetyRatings = new Set<string>(carVariants.map(cv => cv.safetyRating));
    this.carVariantList = {
      color: Array.from(colors),
      year: Array.from(years),
      kilometrage: Array.from(kilometrages),
      transmission: Array.from(transmissions),
      drivetrain: Array.from(drivetrains),
      price: Array.from(prices),
      numberOfDoors: Array.from(numbersOfDoors),
      numberOfSeats: Array.from(numbersOfSeats),
      safetyRating: Array.from(safetyRatings)
    }

    for (let variantsKey in this.carVariantList) {
      // @ts-ignore
      if(this.carVariantList[variantsKey].length === 1 && variantsKey !== 'price' && variantsKey !== 'numberOfSeats' && variantsKey !== 'safetyRating') {
          // @ts-ignore
          this.form.controls[variantsKey].setValue(this.carVariantList[variantsKey][0]);
      }
    }
  }

  initCarEngineSets(carVariants: CarVariantData[]): void{
    const typeOfFuels = new Set<string>(carVariants.map(cv => cv.carEngineResponse.typeOfFuel));
    const displacements = new Set<string>(carVariants.map(cv => cv.carEngineResponse.displacement));
    const horsepowers = new Set<number>(carVariants.map(cv => cv.carEngineResponse.horsepower));
    const fuelEconomys = new Set<string>(carVariants.map(cv => cv.carEngineResponse.fuelEconomy));
    const torques = new Set<number>(carVariants.map(cv => cv.carEngineResponse.torque));
    const cylinders = new Set<number>(carVariants.map(cv => cv.carEngineResponse.cylinders));
    this.carEngineList = {
      typeOfFuel: Array.from(typeOfFuels),
      displacement: Array.from(displacements),
      horsepower: Array.from(horsepowers),
      fuelEconomy: Array.from(fuelEconomys),
      torque: Array.from(torques),
      cylinders: Array.from(cylinders)
    }
    for (let variantsKey in this.carEngineList) {
      // @ts-ignore
      if(this.carEngineList[variantsKey].length === 1 && variantsKey !== 'cylinders' && variantsKey !== 'torque') {
        // @ts-ignore
        this.form.controls[variantsKey].setValue(this.carEngineList[variantsKey][0]);
      }
    }
  }

  clearChoice() : void{
    this.form.reset();
    this.currentVariants = this.carVariants;
    this.initCarVariantSets(this.currentVariants);
    this.initCarEngineSets(this.currentVariants);
    this.countOfSelectFunction = 0;
  }

  protected readonly Number = Number;
}

