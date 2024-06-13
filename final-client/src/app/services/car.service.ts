import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { PlpData } from '../models/plp.data';
import { ResponseContainerData } from '../models/response-container.data';
import { DataTableResponseData } from '../models/data-table-response.data';
import { PdpData } from '../models/pdp.data';

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
}
