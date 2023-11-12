from .base_command import BaseCommannd
from ..models.proyect_candidato_asociacion import ProyectCandidato, ProyectCandidatoSchema, CreatedProyectCandidatoJsonSchema
from ..session import Session

class CreateProjectCandidato(BaseCommannd):
    try:
        def __init__(self, id_project,id_candidato):
            self.id_project = {"idProyecto": id_project}
            self.id_candidato = {"idCandidato": id_candidato} 

        def execute(self):
            print(self.id_candidato)
            print(self.id_project)
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
            session.add(project)
            session.commit()
            new_project = CreatedProyectCandidatoJsonSchema().dump(project)
            session.close()
        
            return "new_project"

           

          
        

    except Exception as error:
        # handle the exception
        print(
            "An exception occurred:", error
        )  # An exception occurred: division by zero
