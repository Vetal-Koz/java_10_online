import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthRequestData} from "../models/auth-request.data";
import {map, Observable} from "rxjs";
import {ResponseContainerData} from "../models/response-container.data";
import {LocalStorageService} from "./local-storage.service";
import {AuthResponseData} from "../models/auth-response.data";
import {User} from "../models/principal/user.data";
import {Router} from "@angular/router";
import {HeaderComponent} from "../layout/header/header.component";

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  authenticated = false;

  BEARER: string = 'Bearer ';

  private authUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient,
              private router: Router,
  ) {}

  signIn(authData: AuthRequestData) {
    this.http.post<ResponseContainerData<AuthResponseData>>(this.authUrl + '/login', authData)
      .pipe(
        map(container => container.data),
      )
      .subscribe(res => {
          localStorage.setItem('token', this.BEARER + res.token.toString() || "");
          this.authenticated = true;
          localStorage.setItem('email', authData.email);
          this.router.navigate(['/wishList']);
        }
      );
  }

  register(authData: AuthRequestData) {
    this.http.post<ResponseContainerData<AuthResponseData>>(this.authUrl + '/register', authData)
      .pipe(
        map(container => container.data),
      )
      .subscribe(res => {
        localStorage.setItem('token', res.token.toString() || "");
        this.authenticated = true;
        localStorage.setItem('email', authData.email);
        this.router.navigate(['/cars']);
      })
  }

  logOut(){
    localStorage.setItem('token', 'dasdada');
    localStorage.removeItem('email');
  }
}
