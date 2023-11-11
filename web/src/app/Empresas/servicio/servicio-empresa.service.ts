import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ServicioEmpresaService {
  private backUrl: string = environment.baseUrl + '/users';

  constructor(private http: HttpClient) {}

  empresaRegistro(empresa: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/empresa`, empresa);
  }

  listarEmpresas(): Observable<any> {
    return this.http.get<any>(`${this.backUrl}/empresa`);
  }

  listarEquipo(): Observable<any> {
    return this.http.get<any>(`${this.backUrl}/equipo/listar-equipos`);
  }

  crearEquipo(equipo: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/equipo/crear-equipos`, equipo);
  }
}
