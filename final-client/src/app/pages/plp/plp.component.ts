import { Component, OnInit } from '@angular/core';
import { PlpData } from '../../models/plp.data';
import { CarService } from '../../services/car.service';
import {DataTableRequest} from "../../models/data-table-request.data";
import {range} from "rxjs";

@Component({
  selector: 'app-plp',
  templateUrl: './plp.component.html',
  styleUrl: './plp.component.css'
})
export class PlpComponent implements OnInit{
  cars?: PlpData[];

  countOfItems?: number;

  countOfPages?: number;

  constructor(private carService: CarService) {}

  loading: boolean = true;

  getCars() :void {
    this.carService.findAll()
    .subscribe(res => {
      this.cars = res.items;
    })
    this.loading = false;
  }

  ngOnInit(): void {
    this.getCars();
  }

  protected readonly range = range;
}
