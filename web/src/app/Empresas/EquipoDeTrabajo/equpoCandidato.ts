export class EquipoCandidato {
  id: number;
  idEquipo: number;
  idCandidato: number;

  constructor(id: number, idEquipo: number, idCandidato: number) {
    this.id = id;
    this.idEquipo = idEquipo;
    this.idCandidato = idCandidato;
  }
}

export type equipoCandidatoCrear = Pick<
  EquipoCandidato,
  'idEquipo' | 'idCandidato'
>;
