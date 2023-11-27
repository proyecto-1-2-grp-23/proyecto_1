import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ServicioCandidatosService {
  private backUrl: string = environment.baseUrlUsuarios + '/users';

  constructor(private http: HttpClient) {}

  candidatoRegistroPersonal(usuario: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}`, usuario);
  }

  candidatoSignIn(usuario: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/singIn`, usuario);
  }

  obtenerCandidatos(): Observable<any> {
    return this.http.get<any>(`${this.backUrl}/candidatos`);
  }

  obtenerCandidatoPorId(id: any): Observable<any> {
    return this.http.get<any>(`${this.backUrl}/candidatos/` + id);
  }

  obtenerCandidatosHabTec(tecnica: any): Observable<any> {
    return this.http.get<any>(
      `${this.backUrl}/candidatos/caracteristicas-tecnicas/` + tecnica
    );
  }

  obtenerCandidatosHabPer(personal: any): Observable<any> {
    return this.http.get<any>(
      `${this.backUrl}/candidatos/caracteristicas-personalidad/` + personal);
  }

  obtenerCandidatosRecomen(tecnica: any, personalidad:any): Observable<any>{

    let params = new HttpParams();
    params = params.set('tecnica', tecnica);
    params = params.set('personalidad', personalidad);

    return this.http.get<any>(
      `${this.backUrl}/candidatos/recomendados`,
      { params: params }
    );
  }
}
