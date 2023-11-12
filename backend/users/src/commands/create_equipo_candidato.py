from .base_command import BaseCommannd
from ..models.equipo_candidato import (
    EquipoCandidato,
    EquipoCandidatoSchema,
    CreatedEquipoCandidatoJsonSchema,
)
from ..session import Session
from ..errors.errors import IncompleteParams, UserAlreadyExists


class CreateEquipoCandidato(BaseCommannd):
    try:

        def __init__(self, data):
            self.data = data

        def execute(self):
            equipo_data = {
                "idEquipo": self.data.pop("idEquipo"),
                "idCandidato": self.data.pop("idCandidato"),
            }
            #

            posted_data = EquipoCandidatoSchema(only=("idEquipo", "idCandidato")).load(
                equipo_data
            )

            equipo = EquipoCandidato(**posted_data)

            session = Session()

            if self.candidato_exist(
                session, posted_data["idCandidato"], posted_data["idEquipo"]
            ):
                session.close()
                raise UserAlreadyExists()

            session.add(equipo)
            session.commit()
            new_equipo = CreatedEquipoCandidatoJsonSchema().dump(equipo)
            session.close()

            return new_equipo

    except Exception as error:
        # handle the exception
        print(
            "An exception occurred:", error
        )  # An exception occurred: division by zero

    def candidato_exist(self, session, candidato, equipo):
        print("CANDIDADO")
        print(candidato)
        return (
            len(
                session.query(EquipoCandidato)
                .filter(
                    EquipoCandidato.idEquipo == equipo,
                    EquipoCandidato.idCandidato == candidato,
                )
                .all()
            )
            > 0
        )
