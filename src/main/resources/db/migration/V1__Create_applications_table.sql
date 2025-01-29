CREATE TABLE applications (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(13) NOT NULL,
  desired_role VARCHAR(255) NOT NULL,
  education VARCHAR(255) NOT NULL,
  resume_path VARCHAR(255) NOT NULL,
  observations TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ip_address VARCHAR(45)
);