import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ServicioProyectosService {
  private backUrl: string = environment.baseUrl + '/projects';

  constructor(private http: HttpClient) {}

  crearProyectos(entrevista: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}`, entrevista);
  }

  listarProyectos(): Observable<any> {
    return this.http.get<any>(`${this.backUrl}/listar-projects`);
  }

  listarProyectosById(id: number): Observable<any> {
    return this.http.get<any>(`${this.backUrl}/${id}/listar-projects`);
  }

  getDataWithQueryParams(queryParam1: string, queryParam2: string): Observable<any> {
    // Build the query parameters using HttpParams.
    const params = new HttpParams()
      .set('personalidad', queryParam1)
      .set('habilidades', queryParam2);

    // Make the GET request with query parameters.
    return this.http.get<any>(`${this.backUrl}/tecnicas_blandas`, { params });
  }
}
