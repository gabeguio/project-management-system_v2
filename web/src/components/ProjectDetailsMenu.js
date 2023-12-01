function projectDetailsMenu() {
  return `
        <div class="project__menu">
            <button class="project__btn" id="about-btn">About</button>
            <button class="project__btn" id="members-btn">Members</button>
            <button class="project__btn" id="tasks-btn">Tasks</button>
        </div>
    `;
}

function configureProjectDetailsMenu() {
  const aboutButton = document.getElementById("about-btn");
  const tasksButton = document.getElementById("tasks-btn");
  const membersButton = document.getElementById("members-btn");
  const aboutContent = document.getElementById("about-content");
  const tasksContent = document.getElementById("tasks-content");
  const membersContent = document.getElementById("members-content");

  // Initially, show the "about" content.
  aboutButton.classList.add("project__btn-selected");
  tasksContent.style.display = "none";
  membersContent.style.display = "none";


  aboutButton.addEventListener("click", () => {
    // Show the "about" content and hides the "tasks" and "members" content.
    aboutButton.classList.add("project__btn-selected");
    tasksButton.classList.remove("project__btn-selected");
    membersButton.classList.remove("project__btn-selected");
    aboutContent.style.display = "block";
    tasksContent.style.display = "none";
    membersContent.style.display = "none";
  });

  tasksButton.addEventListener("click", () => {
    // Show the "tasks" content and hides the "about" and "members" content.
    aboutButton.classList.remove("project__btn-selected");
    tasksButton.classList.add("project__btn-selected");
    membersButton.classList.remove("project__btn-selected");
    aboutContent.style.display = "none";
    tasksContent.style.display = "block";
    membersContent.style.display = "none";
  });

  membersButton.addEventListener("click", () => {
    // Show the "members" content and hides the "about" and "tasks" content.
    aboutButton.classList.remove("project__btn-selected");
    tasksButton.classList.remove("project__btn-selected");
    membersButton.classList.add("project__btn-selected");
    aboutContent.style.display = "none";
    tasksContent.style.display = "none";
    membersContent.style.display = "block";
  });
}

export { projectDetailsMenu, configureProjectDetailsMenu };
