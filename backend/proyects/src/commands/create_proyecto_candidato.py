from .base_command import BaseCommannd
from ..models.proyect_candidato import (
    ProyectCandidato,
    ProyectCandidatoSchema,
    CreatedProyectCandidatoJsonSchema,
)
from ..session import Session
from ..errors.errors import IncompleteParams, UserAlreadyExists


class CreateProjectCandidato(BaseCommannd):
    try:

        def __init__(self, data):
            self.data = data

        def execute(self):
            project_data = {
                "idProyecto": self.data.pop("idProyecto"),
                "idCandidato": self.data.pop("idCandidato"),
            }
            #

            posted_data = ProyectCandidatoSchema(
                only=("idProyecto", "idCandidato")
            ).load(project_data)

            project = ProyectCandidato(**posted_data)

            session = Session()

            if self.candidato_exist(
                session, posted_data["idCandidato"], posted_data["idProyecto"]
            ):
                session.close()
                raise UserAlreadyExists()

            session.add(project)
            session.commit()
            new_project = CreatedProyectCandidatoJsonSchema().dump(project)
            session.close()

            return new_project

    except Exception as error:
        print("An exception occurred:", error)

    def candidato_exist(self, session, candidato, proyecto):
        return (
            len(
                session.query(ProyectCandidato)
                .filter(
                    ProyectCandidato.idProyecto == proyecto,
                    ProyectCandidato.idCandidato == candidato,
                )
                .all()
            )
            > 0
        )
