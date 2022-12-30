import { Production } from './../model/production-model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductionService {

  constructor(private httpClient: HttpClient,
    private toastr: ToastrService) {
  }

  productionById(idProduction:number): Observable<Production>{
    return this.httpClient.get<Production>(`${environment.API_PATH}production/${idProduction}`).pipe(
      catchError(erro => this.exibirErro(erro))
    );
  }


  postProduction$(production: Production, idPlot:number): Observable<Production> {
    return this.httpClient.post<Production>(`${environment.API_PATH}production/${idPlot}/plot`, production).pipe(
      catchError(erro => this.exibirErro(erro))
    );
  }

  listProductionByIdPlot$(idPlot:number): Observable<Array<Production>> {
    return this.httpClient.get<Array<Production>>(`${environment.API_PATH}production/${idPlot}/idplot`).pipe(
      catchError(erro => this.exibirErro(erro))
    );
  }

  putProduction$(production: Production, idProduction:number): Observable<Production>{
    return this.httpClient.put<Production>(`${environment.API_PATH}production/${idProduction}`, production).pipe(
      catchError(erro => this.exibirErro(erro))
    );
  }

  deleteProduction$(idProduction:number): Observable<Production>{
    return this.httpClient.delete<Production>(`${environment.API_PATH}production/${idProduction}`).pipe(
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
