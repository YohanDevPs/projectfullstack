import { DialogFilterNumbersComponent } from './../dialog-filter-numbers/dialog-filter-numbers.component';
import { Plot } from '../../../model/plot-model';
import { PlotService } from '../../../service/plot-service.service';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-plot-form',
  templateUrl: './plot-form.component.html',
  styleUrls: ['./plot-form.component.scss']
})
export class PlotFormComponent implements OnInit {

  plot: Plot = new Plot();

  idFarm: number
  idPlotForEdit: number;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private plotService: PlotService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
      let idPlot = this.route.snapshot.params['idPlot'];
      this.idPlotForEdit = idPlot;
      if(idPlot) {
        this.plotService.plotById(idPlot).subscribe(plot => this.plot = plot);
      }
      this.idFarm = this.route.snapshot.params['idFarm'];
  }

  salvarPlot(){

    let numberDefineCase:number = this.defineCase(this.plot.plotAreaInHectare, this.plot.idPlot);

    switch(numberDefineCase){
      case 1 : this.plotService.postPlot$(this.plot, this.idFarm).subscribe(() => this.cancelar()); break;

      case 2 :this.plotService.putPlot$(this.plot.idPlot , this.plot).subscribe(() => this.cancelar()); break;

      case 3 : this.filterNumberDialog(); break;
    }
  }

  defineCase(plotAreaInHectare: number, idPlot: number): number{
    let numberDefineCase = 0;

    if(this.plot.plotAreaInHectare > 0 && this.plot.idPlot == undefined){ return  numberDefineCase = 1 }

    if(this.plot.plotAreaInHectare > 0 && this.plot.idPlot > 0){ return  numberDefineCase = 2 }

      return numberDefineCase = 3;
  }

  filterNumberDialog(): void {
    const dialogRef = this.dialog.open(DialogFilterNumbersComponent, {
      width: '250px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.cancelar();
    });
  }

  cancelar(){
    this.router.navigate(['listPlot', this.idFarm]);
  }
}
