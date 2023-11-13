from .base_command import BaseCommannd
from ..models.project import Project, ProjectSchema, CreatedProjectJsonSchema
from ..session import Session


class CreateProject(BaseCommannd):
    try:

        def __init__(self, data):
            self.data = data

        def execute(self):
            body: dict = self.data

            posted_data = ProjectSchema(
                only=(
                    "idEmpresa",
                    "nombre",
                    "descripcion",
                    "perfiles",
                    "conocimientos_tecnicos",
                    "habilidades_blandas",
                    "startDate",
                    "finishDate",
                )
            ).load(body)

            project = Project(**posted_data)
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
