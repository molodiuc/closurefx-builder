; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{C27154D1-751C-459F-88D7-77AD22BB11A3}
AppName=Closure FX Builder
AppVersion=1.0
;AppVerName=Closure FX Builder 1.0
AppPublisher=DigiArea, Inc.
AppPublisherURL=http://digi-area.com/
AppSupportURL=http://digi-area.com/ClosureFX/
AppUpdatesURL=http://digi-area.com/ClosureFX/
DefaultDirName={pf}\ClosureFXBuilder
DefaultGroupName=Closure FX Builder
OutputDir=D:\workspaces\eclipse-closurefx\ClosureFX\dist\bundles
OutputBaseFilename=ClosureFXInstall
SetupIconFile=D:\workspaces\eclipse-closurefx\ClosureFX\dist\bundles\ClosureFX\ClosureFX.ico
WizardImageFile=D:\workspaces\eclipse-closurefx\ClosureFX\package\windows\ClosureFX-setup-image.bmp
WizardSmallImageFile=D:\workspaces\eclipse-closurefx\ClosureFX\package\windows\ClosureFX-setup-icon.bmp
Compression=lzma
SolidCompression=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked
Name: "quicklaunchicon"; Description: "{cm:CreateQuickLaunchIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked; OnlyBelowVersion: 0,6.1

[Files]
Source: "D:\workspaces\eclipse-closurefx\ClosureFX\dist\bundles\ClosureFX\ClosureFX.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "D:\workspaces\eclipse-closurefx\ClosureFX\dist\bundles\ClosureFX\runtime\*"; DestDir: "{app}\runtime\"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "D:\workspaces\eclipse-closurefx\ClosureFX\dist\bundles\ClosureFX\app\*"; DestDir: "{app}\app\"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "D:\workspaces\eclipse-closurefx\ClosureFX\resources\ClosureFX-EULA.html"; DestDir: "{app}";
Source: "D:\workspaces\eclipse-closurefx\ClosureFX\resources\GettingStarted.html"; DestDir: "{app}";
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{group}\Closure FX Builder"; Filename: "{app}\ClosureFX.exe"
Name: "{group}\{cm:UninstallProgram,Closure FX Builder}"; Filename: "{uninstallexe}"
Name: "{commondesktop}\Closure FX Builder"; Filename: "{app}\ClosureFX.exe"; Tasks: desktopicon
Name: "{userappdata}\Microsoft\Internet Explorer\Quick Launch\Closure FX Builder"; Filename: "{app}\ClosureFX.exe"; Tasks: quicklaunchicon

[Run]
Filename: "{app}\ClosureFX.exe"; Description: "{cm:LaunchProgram,Closure FX Builder}"; Flags: nowait postinstall skipifsilent

