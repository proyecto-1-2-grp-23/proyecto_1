import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
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
}
