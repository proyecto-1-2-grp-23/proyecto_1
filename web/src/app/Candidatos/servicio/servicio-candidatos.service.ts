import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ServicioCandidatosService {
  private backUrl: string = environment.baseUrl + '/users';

  constructor(private http: HttpClient) {}

  candidatoRegistroPersonal(usuario: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}`, usuario);
  }

  candidatoSignIn(usuario: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/singIn`, usuario);
  }

  obtenerCandidatos(): Observable<any> {
    return this.http.get<any>(`${this.backUrl}/users`);
  }
}
