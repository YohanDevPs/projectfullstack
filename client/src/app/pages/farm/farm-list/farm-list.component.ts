import { Farm } from 'src/app/model/farm-model';
import { FarmService } from '../../../service/farm-service.service';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { DialogDeleteFarmComponent } from '../dialog-delete-farm/dialog-delete-farm.component';

@Component({
  selector: 'app-farm-list',
  templateUrl: './farm-list.component.html',
  styleUrls: ['./farm-list.component.scss']
})
export class FarmListComponent implements OnInit {

  farms: Array<Farm> = [];
  displayedColumns: string[] = ['home', 'nameFarm', 'action']

  constructor(private router: Router,
    private route:ActivatedRoute,
    private farmService: FarmService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.farmList();
  }

  private farmList(): void{
    this.farmService.listFarms$.subscribe(farms => {
      this.farms = farms;
    });
  }

  warningDeleteFarm(fazenda: Farm): void {
    const dialogRef = this.dialog.open(DialogDeleteFarmComponent, {
      width: '450px',
      data: {farm: fazenda}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.farmList();
    });
  }

  addNewFarm(){
    this.router.navigate(['farmform-cadastro'])
  }

  editFarm(farm: Farm){
    this.router.navigate(['farmform-edit', farm.id])
  }

  addPlotInFarm(farm: Farm){
    this.router.navigate(['listPlot', farm.id])
  }

}
