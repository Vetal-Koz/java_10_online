import { Injectable } from '@angular/core';
import {map, Observable} from "rxjs";
import {User} from "../models/principal/user.data";
import {ResponseContainerData} from "../models/response-container.data";
import {HttpClient} from "@angular/common/http";
import {UserResponseData} from "../models/user-response.data";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private authUrl = 'http://localhost:8080/api/auth/user';

  private userUrl = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient) { }

  getCurrentPrincipal(): Observable<User>{
    return this.http.get<ResponseContainerData<User>>(this.authUrl).pipe(
      map(container => container.data),
    )
  }

  loadCurrentUserData(email: string): Observable<UserResponseData>{
    return this.http.get<ResponseContainerData<UserResponseData>>(this.userUrl + `/${email}`)
      .pipe(
        map(container => container.data),
      )
  }

  attachCarVariantToUser(userId: number, carVariantId: number): Observable<boolean>{
    return this.http.post<ResponseContainerData<boolean>>(this.userUrl + `/${userId}` + '/attached' + `/${carVariantId}`,
      {method: 'Add carVariant to user'})
      .pipe(
        map(container => container.data),
      )
  }
}
