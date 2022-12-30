import { ProductionService } from './../../../service/production.service';
import { Production } from './../../../model/production-model';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-delete-production',
  templateUrl: './dialog-delete-production.component.html',
  styleUrls: ['./dialog-delete-production.component.scss']
})
export class DialogDeleteProductionComponent implements OnInit {

  production: Production;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: {production: Production},
    public dialogRef: MatDialogRef<DialogDeleteProductionComponent>,
    public productionService: ProductionService
    ) { }

  ngOnInit(): void {
    this.production = this.data.production;
  }

  cancel(): void {
    this.dialogRef.close();
  }

  confirmationDelete(){
    this.productionService.deleteProduction$(this.production.idProduction).subscribe(() => this.dialogRef.close())
  }
}
