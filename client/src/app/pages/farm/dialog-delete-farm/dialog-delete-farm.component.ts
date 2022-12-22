import { Farm } from 'src/app/model/farm-model';
import { FarmService } from 'src/app/service/farm-service.service';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-delete-farm',
  templateUrl: './dialog-delete-farm.component.html',
  styleUrls: ['./dialog-delete-farm.component.scss']
})
export class DialogDeleteFarmComponent implements OnInit {

  farm: Farm;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: {farm: Farm},
    public dialogRef: MatDialogRef<DialogDeleteFarmComponent>,
    public farmService: FarmService
    ) { }

  ngOnInit(): void {
    this.farm = this.data.farm;
  }

  cancel(): void {
    this.dialogRef.close();
  }

  confirmationDelete(){
    this.farmService.delete$(this.farm.id).subscribe(() => this.dialogRef.close())
  }

}
