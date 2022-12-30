import { Plot } from './../model/plot-model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { EMPTY, Observable } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PlotService {

  constructor(private httpClient: HttpClient,
    private toastr: ToastrService) {
  }

  plotById(id:number): Observable<Plot>{
    return this.httpClient.get<Plot>(`${environment.API_PATH}plot/${id}`).pipe(
      catchError(erro => this.exibirErro(erro))
    );
  }

  postPlot$(plot: Plot, idFarm:number): Observable<Plot> {
    return this.httpClient.post<Plot>(`${environment.API_PATH}plot/${idFarm}/farmid`, plot).pipe(
      catchError(erro => this.exibirErro(erro))
    );
  }

  listPlotByIdFarm$(idFarm:number): Observable<Array<Plot>> {
    return this.httpClient.get<Array<Plot>>(`${environment.API_PATH}plot/${idFarm}/farmid`).pipe(
      catchError(erro => this.exibirErro(erro))
    );
  }

  putPlot$(idPlot:number, plot: Plot): Observable<Plot>{
    return this.httpClient.put<Plot>(`${environment.API_PATH}plot/${idPlot}`, plot).pipe(
      catchError(erro => this.exibirErro(erro))
    );
  }

  delete$(idPlot:number): Observable<Plot>{
    return this.httpClient.delete<Plot>(`${environment.API_PATH}plot/${idPlot}`).pipe(
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
