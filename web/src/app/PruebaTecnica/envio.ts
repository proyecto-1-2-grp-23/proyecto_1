export class Envio {
  id: number;
  idProyecto: number;
  idCandidato: number;
  observaciones: string;
  respuestasEnviadas: {
    idRespuesta: number;
    correcta: boolean;
  }[];

  constructor(
    id: number,
    idProyecto: number,
    idCandidato: number,
    observaciones: string,
    respuestasEnviadas: any
  ) {
    this.id = id;
    this.idProyecto = idProyecto;
    this.idCandidato = idCandidato;
    this.observaciones = observaciones;
    this.respuestasEnviadas = respuestasEnviadas;
  }
}

export type envioCrear = Pick<
  Envio,
  'idProyecto' | 'idCandidato' | 'observaciones' | 'respuestasEnviadas'
>;
