import { Component, OnInit } from '@angular/core';
import { PlpData } from '../../models/plp.data';
import { CarService } from '../../services/car.service';

@Component({
  selector: 'app-plp',
  templateUrl: './plp.component.html',
  styleUrl: './plp.component.css'
})
export class PlpComponent implements OnInit{
  cars?: PlpData[];
  
  constructor(private carService: CarService) {}

  loading: boolean = true;

  getCars() :void {
    this.carService.findAll()
    .subscribe(res => this.cars = res.items)
    this.loading = false;
  }

  ngOnInit(): void {
    this.getCars();
  }
}
