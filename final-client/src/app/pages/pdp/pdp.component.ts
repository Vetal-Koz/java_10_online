import { Component, OnInit } from '@angular/core';
import { PdpData } from '../../models/pdp.data';
import { CarVariantData } from '../../models/car-variant-data';
import { CarEngineData } from '../../models/car-engine.data';
import { CarService } from '../../services/car.service';
import { ActivatedRoute } from '@angular/router';


interface carVariantList {

  color: string[];
  
  year: number[];

  kilometrage: string[];

  transmission: string[];

  drivetrain: string[];

  price: string[];

  numberOfDoors: number[];

  numberOfSeats: number[];

  safetyRating: string[];
}


interface carEngineList {

  typeOfFuel: string[],

  displacement: string[],

  horsepower: number[],

  fuelEconomy: string[],

  torque: number[],

  cylinders: number[];
}

const defaultCarVariantList: carVariantList = {
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
  car?: PdpData;

  carVariant?: CarVariantData;

  carEngine?: CarEngineData;
  
  carVariantList = defaultCarVariantList;

  carEngineList = defaultCarEngineList;

  constructor(private carService: CarService, private route: ActivatedRoute) {}

  getPdpById(id: number){
    this.carService.findById(id)
    .subscribe(res => {
      this.car = res,
      console.log(res.brand),
      console.log(res.carImages),
      console.log(res.carVariantResponses)
      this.initCarVariantSets(res.carVariantResponses),
      this.initCarEngineSets(res.carVariantResponses)
    });
    
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
  }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'))
    this.getPdpById(id);
   
  }



}

