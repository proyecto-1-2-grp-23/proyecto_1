import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ServicioUsuariosService {
  private backUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) {}

  candidatoSignIn(usuario: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/users/login`, usuario);
  }
}
