import {Component, OnInit} from '@angular/core';
import {AuthenticateService} from "../../services/authenticate.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{
  userEmail = localStorage.getItem('email');
  isAuthenticated = false;

  constructor(private authService: AuthenticateService, private router: Router) {}

  logOut(){
    this.isAuthenticated = false;
    this.authService.logOut();
    this.router.navigate(['/cars']);
  }

  ngOnInit(): void {
    if (this.userEmail != null) {
      this.isAuthenticated = true;
    }else {
      this.isAuthenticated = false;
    }

  }
}
