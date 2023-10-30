import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ServicioCandidatosService {
  private backUrl: string = 'http://localhost:5000'; //environment.baseUrl

  constructor(private http: HttpClient) {}

  candidatoRegistroPersonal(usuario: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/users`, usuario);
  }

  candidatoSignIn(usuario: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/singIn`, usuario);
  }
}
