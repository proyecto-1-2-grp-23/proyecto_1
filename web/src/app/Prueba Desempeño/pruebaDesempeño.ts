export class PruebaDesempeño {
  idPrueba: number;
  idCandidato: number;
  idEmpresa: number;
  habilidad: string;
  puntaje: number;

  constructor(
    idPrueba: number,
    idCandidato: number,
    idEmpresa: number,
    habilidad: string,
    puntaje: number
  ) {
    this.idPrueba = idPrueba;
    this.idCandidato = idCandidato;
    this.idEmpresa = idEmpresa;
    this.habilidad = habilidad;
    this.puntaje = puntaje;
  }
}

export type pruebaDesempeñoCrear = Pick<
  PruebaDesempeño,
  'idPrueba' | 'idCandidato' | 'idEmpresa' | 'habilidad' | 'puntaje'
>;
