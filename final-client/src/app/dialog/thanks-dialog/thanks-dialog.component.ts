import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-thanks-dialog',
  templateUrl: './thanks-dialog.component.html',
  styleUrl: './thanks-dialog.component.css'
})
export class ThanksDialogComponent implements OnInit{
  constructor(
    public dialog: MatDialogRef<ThanksDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public message: string) { }

  closeDialog(): void {
    this.dialog.close(false);
  }
  confirm(): void {
    this.dialog.close(true);
  }

  ngOnInit() {
  }

}


