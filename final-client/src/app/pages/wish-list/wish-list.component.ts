import {Component, OnInit} from '@angular/core';
import {AuthenticateService} from "../../services/authenticate.service";
import {User} from "../../models/principal/user.data";
import {UserService} from "../../services/user.service";
import {UserResponseData} from "../../models/user-response.data";
import {MatDialog} from "@angular/material/dialog";
import {ThanksDialogComponent} from "../../dialog/thanks-dialog/thanks-dialog.component";

@Component({
  selector: 'app-wish-list',
  templateUrl: './wish-list.component.html',
  styleUrl: './wish-list.component.css'
})
export class WishListComponent implements OnInit{

  userEmail = localStorage.getItem('email')

  user?: UserResponseData;

  constructor(private userService: UserService, public dialog: MatDialog) {}

  openDialog(): void {
    this.dialog
      .open(ThanksDialogComponent, {
        data: `We call you soon`
      })
  }

  ngOnInit(): void {
    if(this.userEmail !== null) {
      this.userService.loadCurrentUserData(this.userEmail).subscribe(res => this.user = res);
    }
  }
}
