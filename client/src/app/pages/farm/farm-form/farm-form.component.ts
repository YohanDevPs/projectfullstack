import { Farm } from 'src/app/model/farm-model';
import { FarmService } from 'src/app/service/farm-service.service';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CATCH_ERROR_VAR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-farm-form',
  templateUrl: './farm-form.component.html',
  styleUrls: ['./farm-form.component.scss']
})
export class FarmFormComponent implements OnInit {

  farm: Farm = new Farm();

  constructor(private router: Router,
    private route: ActivatedRoute,
    private farmService: FarmService) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params['idFarm'];
    if(id) {
      this.farmService.farmById(id).subscribe(farm => this.farm = farm);
    }
  }

  salvar(){
    this.farm.id ?
      this.farmService.put$(this.farm.id, this.farm).subscribe(() => this.cancelar()) :
      this.farmService.post$(this.farm).subscribe(() => this.cancelar());
  }

  cancelar(){
    this.router.navigate(['farms']);
  }

}
