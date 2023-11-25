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

  crearProyectos(proyecto: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}`, proyecto);
  }

  listarProyectos(idEmpresa: any): Observable<any> {
    return this.http.get<any>(`${this.backUrl}/listar-projects/` + idEmpresa);
  }

  listarProyectosById(id: number): Observable<any> {
    return this.http.get<any>(`${this.backUrl}/listar-project/` + id);
  }

  agregarCandidatoProyecto(candidatoProyecto: any): Observable<any> {
    return this.http.post<any>(
      `${this.backUrl}/proyecto-candidato`,
      candidatoProyecto
    );
  }

  listarCandidatosProyecto(id: number): Observable<any> {
    return this.http.get<any>(`${this.backUrl}/listar-candidatos/` + id);
  }

  modificarProyectos(proyecto: any): Observable<any> {
    return this.http.put<any>(`${this.backUrl}`, proyecto);
  }
}
