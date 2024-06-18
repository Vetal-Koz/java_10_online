import { Component } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {AuthenticateService} from "../../services/authenticate.service";
import {Router} from "@angular/router";
import {AuthRequestData} from "../../models/auth-request.data";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm = this.formBuilder.group({
    email: [null, [Validators.required, Validators.email]],
    password: [null, Validators.required],
  })

  constructor(private authService: AuthenticateService,
              private formBuilder: FormBuilder,
              private router: Router) {}

  login(){
    if(this.registerForm.valid) {
      const authData: AuthRequestData =
        {
          email: String(this.registerForm.value.email),
          password: String(this.registerForm.value.password)
        };
      this.authService.register(authData);
    }
  }
}
