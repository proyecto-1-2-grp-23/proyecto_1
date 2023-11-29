import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ServicioPruebaTecnicaService {
  private backUrl: string = environment.baseUrlPruebas + '/pruebas';

  constructor(private http: HttpClient) {}

  crearPreguntas(pregunta: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/preguntas`, pregunta);
  }

  listarPreguntas(): Observable<any> {
    return this.http.get<any>(`${this.backUrl}/preguntas`);
  }

  listarPreguntasProyecto(idProyecto: number): Observable<any> {
    return this.http.get<any>(
      `${this.backUrl}/preguntas/proyectos/` + idProyecto
    );
  }

  crearEnvio(envio: any): Observable<any> {
    return this.http.post<any>(`${this.backUrl}/respuesta-enviada`, envio);
  }

  consultarResultado(idProyecto: number, idCandidato: number): Observable<any> {
    return this.http.get<any>(
      `${this.backUrl}/proyectos/` + idProyecto + `/candidatos/` + idCandidato
    );
  }
}
