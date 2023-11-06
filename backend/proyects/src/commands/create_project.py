from .base_command import BaseCommannd
from ..models.project import Project, ProjectSchema, CreatedProjectJsonSchema
from ..session import Session

class CreateProject(BaseCommannd):
    try:
        def __init__(self, data):
            self.data = data

        def execute(self):
            
            project_data = {
                "nombre": self.data.pop("nombre"),
                "descripcion": self.data.pop("descripcion"),
                "perfiles": self.data.pop("perfiles"),
                "conocimientos_tecnicos": self.data.pop("conocimientos_tecnicos"),
                "habilidades_blandas": self.data.pop("habilidades_blandas")
            }

            posted_data = ProjectSchema(
                only=(
                    "nombre",
                    "descripcion",
                    "perfiles",
                    "conocimientos_tecnicos",
                    "habilidades_blandas"
                )
            ).load(project_data)
            

            project = Project(**project_data)
            session = Session()
            session.add(project)
            session.commit()
            new_project = CreatedProjectJsonSchema().dump(project)
            session.close()
        
            return new_project

           

          
        

    except Exception as error:
        # handle the exception
        print(
            "An exception occurred:", error
        )  # An exception occurred: division by zero
