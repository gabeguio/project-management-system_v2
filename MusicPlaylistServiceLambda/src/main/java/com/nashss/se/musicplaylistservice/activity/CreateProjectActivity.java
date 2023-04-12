package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.CreatePlaylistRequest;
import com.nashss.se.musicplaylistservice.activity.requests.CreateProjectRequest;
import com.nashss.se.musicplaylistservice.activity.results.CreatePlaylistResult;
import com.nashss.se.musicplaylistservice.activity.results.CreateProjectResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.converters.ProjectModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.PlaylistDao;
import com.nashss.se.musicplaylistservice.dynamodb.ProjectDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Playlist;
import com.nashss.se.musicplaylistservice.dynamodb.models.Project;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.musicplaylistservice.models.PlaylistModel;
import com.nashss.se.musicplaylistservice.models.ProjectModel;
import com.nashss.se.projectresources.music.playlist.servic.util.MusicPlaylistServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the CreatePlaylistActivity for the MusicPlaylistService's CreatePlaylist API.
 * <p>
 * This API allows the customer to create a new playlist with no songs.
 */
public class CreateProjectActivity {
    private final Logger log = LogManager.getLogger();
    private final ProjectDao projectDao;

    /**
     * Instantiates a new CreateProjectActivity object.
     *
     * @param projectDao ProjectDao to access the projects table.
     */
    @Inject
    public CreateProjectActivity( ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * This method handles the incoming request by persisting a new playlist
     * with the provided playlist name and customer ID from the request.
     * <p>
     * It then returns the newly created playlist.
     * <p>
     * If the provided playlist name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createProjectRequest    request object containing the projectId associated with it
     * @return createPlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    public CreateProjectResult handleRequest(final CreateProjectRequest createProjectRequest) {
        log.info("Received CreateProjectRequest {}", createProjectRequest);

        if (!MusicPlaylistServiceUtils.isValidString(createProjectRequest.getProjectId())) {
            throw new InvalidAttributeValueException("Project ID [" + createProjectRequest.getProjectId() +
                    "] contains illegal characters");
        }

        if (!MusicPlaylistServiceUtils.isValidString(createProjectRequest.getTitle())) {
            throw new InvalidAttributeValueException("Project Title [" + createProjectRequest.getTitle() +
                    "] contains illegal characters");
        }

        String status = null;
        if (createProjectRequest.getStatus() != null) {
            status = createProjectRequest.getStatus();
        }

        Project newProject = new Project();
        newProject.setProjectId(MusicPlaylistServiceUtils.generatePlaylistId());
        newProject.setTitle(createProjectRequest.getTitle());
        newProject.setDescription(createProjectRequest.getDescription());
        newProject.setStatus(status);

        projectDao.saveProject(newProject);

        ProjectModel projectModel = new ProjectModelConverter().toProjectModel(newProject);
        return CreateProjectResult.builder()
                .withProject(projectModel)
                .build();
    }
}
