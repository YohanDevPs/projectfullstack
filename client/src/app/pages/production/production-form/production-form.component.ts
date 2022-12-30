import { DialogFiterNumberProductionComponent } from './../dialog-fiter-number-production/dialog-fiter-number-production.component';
import { ProductionService } from '../../../service/production.service';
import { Production } from '../../../model/production-model';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-production-form',
  templateUrl: './production-form.component.html',
  styleUrls: ['./production-form.component.scss']
})
export class ProductionFormComponent implements OnInit {

  production: Production = new Production();

  idFarm: number
  idPlot: number
  idProductionForEdit: number;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private productionService: ProductionService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
      const idProduction = this.route.snapshot.params['idProduction'];
      this.idFarm = this.route.snapshot.params['idFarm'];
      this.idProductionForEdit = idProduction;
      if(idProduction) {
        this.productionService.productionById(idProduction).subscribe(production => this.production = production)
      }
      this.idPlot = this.route.snapshot.params['idPlot'];
  }

  salvarProduction(){

    let numberDefineCase:number = this.defineCase(this.production.amount, this.production.idProduction);

    switch(numberDefineCase){
      case 1 : this.productionService.postProduction$(this.production, this.idPlot)
          .subscribe(() => this.cancelar()); break;

      case 2 :this.productionService.putProduction$(this.production, this.production.idProduction)
          .subscribe(() => this.cancelar()); break;

      case 3 : this.filterNumberDialog(); break;
    }
  }

  defineCase(amountProduction: number, idProduction: number): number{
    let numberDefineCase = 0;

    if(this.production.amount >= 0 && this.production.idProduction == undefined){ return  numberDefineCase = 1 }

    if(this.production.amount >= 0 && this.production.idProduction > 0){ return  numberDefineCase = 2 }

      return numberDefineCase = 3;
  }

  filterNumberDialog(): void {
    const dialogRef = this.dialog.open(DialogFiterNumberProductionComponent, {
      width: '250px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.cancelar();
    });
  }

  cancelar(){
    this.router.navigate(['listProduction', this.idPlot, this.idFarm]);
  }

}

