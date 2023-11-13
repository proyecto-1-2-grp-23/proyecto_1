export class ProyectoCandidato {
  id: number;
  idProyecto: number;
  idCandidato: number;

  constructor(id: number, idProyecto: number, idCandidato: number) {
    this.id = id;
    this.idProyecto = idProyecto;
    this.idCandidato = idCandidato;
  }
}

export type proyectoCandidatoCrear = Pick<
  ProyectoCandidato,
  'idProyecto' | 'idCandidato'
>;
