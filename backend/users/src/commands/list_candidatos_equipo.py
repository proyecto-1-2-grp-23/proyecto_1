from .base_command import BaseCommannd
from ..models.candidato import Candidato, CandidatoJsonSchema
from ..models.equipo_candidato import (
    EquipoCandidato,
    EquipoCandidatoSchema,
    EquipoCandidatoJsonSchema,
)
from ..session import Session


class ListCandidatosEquipo(BaseCommannd):
    def __init__(self, idEquipo):
        self.idEquipo = idEquipo

    def execute(self):
        session = Session()
        equipo = (
            session.query(EquipoCandidato)
            .filter(EquipoCandidato.idEquipo == self.idEquipo)
            .all()
        )

        equipoResult_json = []

        for registro in equipo:
            candidatoEq_json = EquipoCandidatoJsonSchema().dump(registro)

            candidato = (
                session.query(Candidato)
                .filter(Candidato.idUsuario == registro.idCandidato)
                .first()
            )
            candidato_json: dict = CandidatoJsonSchema().dump(candidato)

            print("CANDD")
            print(candidato_json)

            candidatoEq_json = {**candidatoEq_json, "candidato": candidato_json}
            equipoResult_json.append(candidatoEq_json)
        session.close()
        return equipoResult_json
