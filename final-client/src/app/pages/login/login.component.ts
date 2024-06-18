import { Component } from '@angular/core';
import {AuthenticateService} from "../../services/authenticate.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthRequestData} from "../../models/auth-request.data";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm = this.formBuilder.group({
    email: [null, [Validators.required, Validators.email]],
    password: [null, Validators.required],
  })

  constructor(private authService: AuthenticateService,
              private formBuilder: FormBuilder,
              private router: Router) {}

  login(){
    if(this.loginForm.valid) {
      const authData: AuthRequestData =
        {
          email: String(this.loginForm.value.email),
          password: String(this.loginForm.value.password)
        };
      this.authService.signIn(authData);
    }
  }
}
