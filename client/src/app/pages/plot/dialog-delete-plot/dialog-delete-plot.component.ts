import { PlotService } from './../../../service/plot-service.service';
import { Plot } from 'src/app/model/plot-model';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-delete-plot',
  templateUrl: './dialog-delete-plot.component.html',
  styleUrls: ['./dialog-delete-plot.component.scss']
})
export class DialogDeletePlotComponent implements OnInit {

  plot: Plot;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: {plot: Plot},
    public dialogRef: MatDialogRef<DialogDeletePlotComponent>,
    public plotService: PlotService
    ) { }

  ngOnInit(): void {
    this.plot = this.data.plot;
  }

  cancel(): void {
    this.dialogRef.close();
  }

  confirmationDelete(){
    this.plotService.delete$(this.plot.idPlot).subscribe(() => this.dialogRef.close())
  }

}
