import { Plot } from 'src/app/model/plot-model';
import { PlotService } from '../../../service/plot-service.service';
import { Farm } from 'src/app/model/farm-model';
import { Production } from '../../../model/production-model';
import { ProductionService } from '../../../service/production.service';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { DialogDeleteProductionComponent } from '../dialog-delete-production/dialog-delete-production.component';
import { DialogDeletePlotComponent } from '../../plot/dialog-delete-plot/dialog-delete-plot.component';

@Component({
  selector: 'app-production-list',
  templateUrl: './production-list.component.html',
  styleUrls: ['./production-list.component.scss']
})
export class ProductionListComponent implements OnInit {

  productions: Array<Production> = [];
  displayedColumns: string[] = ['producao', 'amount', 'action']

  plot: Plot = new Plot();
  farm: Farm = new Farm();
  idPlot: number;
  idFarm: number;

  constructor(private router: Router,
    private route:ActivatedRoute,
    private productionService: ProductionService,
    private plotService: PlotService,
    private dialog: MatDialog) { };

    ngOnInit(): void {
      this.idPlot = this.route.snapshot.params['idPlot'];
      this.idFarm = this.route.snapshot.params['idFarm'];
      if(this.idPlot) {
        this.plotService.plotById(this.idPlot).subscribe(plot => this.plot = plot);
      }
      this.productionList(this.idPlot);
    }

    warningDeleteProduction(producao: Production): void {
      const dialogRef = this.dialog.open(DialogDeleteProductionComponent, {
        width: '450px',
        data: {production: producao}
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.ngOnInit();
      });
    }

    private productionList(idPlot: number): Array<Production>{
      this.productionService.listProductionByIdPlot$(idPlot)
      .subscribe(productions => {
        this.productions = productions;
    });
    return this.productions;
  }

  onAddProduction(){
    console.log("FarmId: ", this.idFarm)
    this.router.navigate(['productionform-cadastro', this.idPlot, this.idFarm]);
  }

  onEditProduction(production: Production){
    this.router.navigate(['productionform-edit', production.idProduction, this.idPlot, this.idFarm]);
  }

  onDeleteProduction(production: Production){
    this.productionService.deleteProduction$(production.idProduction).subscribe(() => this.productionList(this.idPlot))
  }

  onDeletePlot(plot:Plot){
    this.plotService.delete$(plot.idPlot).subscribe(() => this.backPage());
  }

  onEditPlot(){
    this.router.navigate(['plotform-edit', this.idPlot, this.idFarm]);
  }

  backPage(){
    this.router.navigate(['listPlot', this.idFarm]);
  }

}
