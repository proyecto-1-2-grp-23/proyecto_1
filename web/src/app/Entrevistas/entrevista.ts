export class Candidato {
  id: number;
  idFuncionario: number;
  idEmpresa: number;
  idCandidato: number;
  fecha: Date;
  lugar: string;

  constructor(
    id: number,
    idFuncionario: number,
    idEmpresa: number,
    idCandidato: number,
    fecha: Date,
    lugar: string
  ) {
    this.id = id;
    this.idFuncionario = idFuncionario;
    this.idEmpresa = idEmpresa;
    this.idCandidato = idCandidato;
    this.fecha = fecha;
    this.lugar = lugar;
  }
}

export type entrevistaCrear = Pick<
  Candidato,
  'idFuncionario' | 'idEmpresa' | 'idCandidato' | 'fecha' | 'lugar'
>;
