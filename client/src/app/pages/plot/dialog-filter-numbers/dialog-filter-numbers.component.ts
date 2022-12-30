import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Plot } from 'src/app/model/plot-model';
import { PlotService } from 'src/app/service/plot-service.service';

@Component({
  selector: 'app-dialog-filter-numbers',
  templateUrl: './dialog-filter-numbers.component.html',
  styleUrls: ['./dialog-filter-numbers.component.scss']
})
export class DialogFilterNumbersComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DialogFilterNumbersComponent>
    ) { }

  ngOnInit(): void {
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
