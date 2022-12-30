import { Farm } from '../model/farm-model';

import { EMPTY, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class FarmService {

  constructor(private httpClient: HttpClient,
    private toastr: ToastrService) {
  }

  farmById(id:number): Observable<Farm>{
    return this.httpClient.get<Farm>(`${environment.API_PATH}farm/${id}`).pipe(
      catchError(erro => this.exibirErro(erro))
    );
  }

  get listFarms$(): Observable<Array<Farm>>{
    return this.httpClient.get<Array<Farm>>(`${environment.API_PATH}farm`).pipe(
      catchError(erro => this.exibirErro(erro))
    );
  }

  post$(farm?: Farm): Observable<Farm> {
    return this.httpClient.post<Farm>(`${environment.API_PATH}farm`, farm).pipe(
      catchError(erro => this.exibirErro(erro))
    );
  }

  put$(id:number, farm:Farm): Observable<Farm>{
    return this.httpClient.put<Farm>(`${environment.API_PATH}farm/${farm.id}`, farm).pipe(
      catchError(erro => this.exibirErro(erro))
    );
  }

  delete$(id:number): Observable<Farm>{
    return this.httpClient.delete<Farm>(`${environment.API_PATH}farm/${id}`).pipe(
      catchError(erro => this.exibirErro(erro))
    );
  }

  exibirErro(e: any): Observable<any>{
    this.exibirMessagem('ERRO!','Não foi possivel realizar operação!', 'toast-erro');
    return EMPTY;
  }

  exibirMessagem(titulo: string, mensagem: string, tipo: string){
    this.toastr.show(mensagem, titulo, {closeButton: true, progressBar: true}, tipo)
  }

}
