import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-fiter-number-production',
  templateUrl: './dialog-fiter-number-production.component.html',
  styleUrls: ['./dialog-fiter-number-production.component.scss']
})
export class DialogFiterNumberProductionComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DialogFiterNumberProductionComponent>
    ) { }

  ngOnInit(): void {
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
