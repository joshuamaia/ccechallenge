import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class AgentesService {
  constructor(private http: HttpClient) {}

  apiUrl = environment.apiURL;

  cadastrar(arquivo: File) {
    console.log('Entrei no envio de arquivo');
    const formData: FormData = new FormData();

    formData.append('file', arquivo);

    return this.http.post<string[]>(
      `${this.apiUrl}/agentes/upload-xml`,
      formData
    );
  }
}
