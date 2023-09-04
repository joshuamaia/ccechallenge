import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { ConsolidadoRegiaoDto } from 'src/app/model/ConsolidadoRegiaoDto';
import { IRegiao } from 'src/app/model/RegiaoModel';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class RegioesService {
  apiUrl = environment.apiURL;

  constructor(private httpClient: HttpClient) {}

  buscarRegiaoBySigla(sigla: String): Observable<IRegiao[]> {
    return this.httpClient
      .get<IRegiao[]>(`${this.apiUrl}/regioes/findBySigla/${sigla}`)
      .pipe(
        catchError((error) => {
          console.error('Ocorreu um erro ao buscar Regiões: ', error);
          return throwError(() => error);
        })
      );
  }

  consolidadoRegiao(): Observable<ConsolidadoRegiaoDto[]> {
    return this.httpClient
      .get<ConsolidadoRegiaoDto[]>(`${this.apiUrl}/regioes/consolidado/`)
      .pipe(
        catchError((error) => {
          console.error('Ocorreu um erro ao buscar Regiões: ', error);
          return throwError(() => error);
        })
      );
  }
}
