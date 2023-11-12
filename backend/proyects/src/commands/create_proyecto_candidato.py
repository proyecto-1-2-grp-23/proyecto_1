from .base_command import BaseCommannd
from ..models.proyect_candidato import ProyectCandidato, ProyectCandidatoSchema, CreatedProyectCandidatoJsonSchema
from ..session import Session
from ..errors.errors import IncompleteParams, UserAlreadyExists
class CreateProjectCandidato(BaseCommannd):
    try:
        def __init__(self, data):
            self.data = data
        def execute(self):

            project_data = {
                "idProyecto": self.id_project.pop("idProyecto"),
                "idCandidato": self.id_candidato.pop("idCandidato")
            }
    #
            
            posted_data = ProyectCandidatoSchema(
                only=(
                    "idProyecto",
                    "idCandidato"
                )
            ).load(project_data)
            
            
            project = ProyectCandidato(**posted_data)
            
            
            
            session = Session()

            if self.candidato_exist(session, posted_data["idCandidato"], posted_data["idEquipo"]):
                session.close()
                raise UserAlreadyExists()

            session.add(project)
            session.commit()
            new_project = CreatedProyectCandidatoJsonSchema().dump(project)
            session.close()
        
            return new_project

        

    except Exception as error:
        print("An exception occurred:", error)  

    def candidato_exist(self, session, candidato, equipo):
        print("CANDIDATO")
        print(candidato)
        return (
            len(
                session.query(ProyectCandidato)
                .filter(
                    ProyectCandidato.idEquipo == equipo,
                    ProyectCandidato.idCandidato == candidato,
                )
                .all()
            )
            > 0
        )