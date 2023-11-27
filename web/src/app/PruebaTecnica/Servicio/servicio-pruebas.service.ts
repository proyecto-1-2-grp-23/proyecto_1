import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ServicioPruebasService {
  private backUrl: string = environment.baserUrlpruebas + '/pruebas';

  constructor(private http: HttpClient) {}

  crearProyectos(proyecto: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/preguntas`, proyecto);
  }


}
