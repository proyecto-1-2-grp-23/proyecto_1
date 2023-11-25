export type Respuesta = {
  descripcion: string;
};


export class Prueba{
  idProyecto: number;
  dificultad: number;
  descripcion: string;
  respuestas: Respuesta[];

  constructor(idProyecto: number, dificultad: number, descripcion: string, respuestas: Respuesta[]) {
    this.idProyecto = idProyecto;
    this.dificultad = dificultad;
    this.descripcion = descripcion;
    this.respuestas = respuestas;
  }

}

export type pruebaCrear = Pick<
Prueba,
  | 'idProyecto'
  | 'dificultad'
  | 'descripcion'
  | 'respuestas'
>;
