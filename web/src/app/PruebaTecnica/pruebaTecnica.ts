export class PruebaTecnica {
  id: number;
  idProyecto: number;
  dificultad: number;
  descripcion: string;
  respuestas: {
    descripcion: string;
    esCorrecta: boolean;
  }[];

  constructor(
    id: number,
    idProyecto: number,
    dificultad: number,
    descripcion: string,
    respuestas: any
  ) {
    this.id = id;
    this.dificultad = dificultad;
    this.descripcion = descripcion;
    this.respuestas = respuestas;
    this.idProyecto = idProyecto;
  }
}

export type pruebaTecnicaCrear = Pick<
  PruebaTecnica,
  'idProyecto' | 'dificultad' | 'descripcion' | 'respuestas'
>;
