import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { PlpData } from '../models/plp.data';
import { ResponseContainerData } from '../models/response-container.data';
import { DataTableResponseData } from '../models/data-table-response.data';
import { PdpData } from '../models/pdp.data';
import {Data} from "@angular/router";
import {CarIndexData} from "../models/car-index-data";

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(private http: HttpClient) {}

  private carsUrl = 'http://localhost:8080/api/cars';

  findAll(): Observable<DataTableResponseData<PlpData>>  {
    return this.http.get<ResponseContainerData<DataTableResponseData<PlpData>>>(this.carsUrl)
    .pipe(
      map(container => container.data),
    );
  }

  findById(id: number): Observable<PdpData>{
    return this.http.get<ResponseContainerData<PdpData>>(`${this.carsUrl}/${id}`)
    .pipe(
      map(container => container.data),
    );
  }

  carSearch(search: string): Observable<CarIndexData[]> {
    return this.http.get<ResponseContainerData<CarIndexData[]>>(`${this.carsUrl}/search?search=${search}`)
      .pipe(
        map(res => res.data)
      )
  }
}
