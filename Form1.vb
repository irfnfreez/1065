Imports System.Data.OleDb
Imports System.Drawing.Drawing2D
Imports System.Windows.Forms.VisualStyles.VisualStyleElement.StartPanel

Public Class Form1
    Private Const CONNECTION_STRING As String = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=C:\Users\User\Desktop\VB\testVB.mdb"

    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        ' Create a new connection
        Using conn As New OleDbConnection(CONNECTION_STRING)
            Try
                ' Open the connection
                conn.Open()

                ' Create a new data adapter based on the specified query.
                Dim dataAdapter As New OleDbDataAdapter("SELECT * FROM Students", conn)

                ' Create a command builder to generate SQL update, insert, and
                ' delete commands based on select command. These are used to
                ' update the database.
                Dim commandBuilder As New OleDbCommandBuilder(dataAdapter)

                ' Populate a new data table and bind it to the BindingSource.
                Dim table As New DataTable()
                table.Locale = System.Globalization.CultureInfo.InvariantCulture
                dataAdapter.Fill(table)

                ' Bind the table to the DataGridView
                DataGridView1.DataSource = table

            Catch ex As Exception
                MessageBox.Show("An error occurred while loading the data: " & ex.Message)
            End Try
        End Using
    End Sub

    Private Sub ReferenceToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles ReferenceToolStripMenuItem.Click
        MessageBox.Show("This is created by Irfan")
    End Sub

    Private Sub btnShow_Click(sender As Object, e As EventArgs) Handles btnShow.Click
        Try
            Dim name As String
            name = UCase(txtName.Text)

            Dim gender As String
            gender = cboGender.Text

            Dim phone As String
            phone = txtPhone.Text

            Dim course As String
            course = cbCourse.Text

            Dim semester As String
            semester = txtSem.Text

            Dim fee As String
            fee = txtFee.Text

            Dim total As String
            total = txtFee.Text * txtSem.Text

            txtShow.Text = "Name : " + name.ToString + vbNewLine + "Gender : " + gender.ToString + vbNewLine +
                "Phone : " + phone.ToString + vbNewLine + "Course : " + course.ToString + vbNewLine + "Semester : " +
            semester.ToString + vbNewLine + "Fee : RM" + total.ToString


        Catch ex As Exception

        End Try

        MsgBox("Error. Please fill up all details.")

    End Sub

    Private Sub btnExit_Click(sender As Object, e As EventArgs) Handles btnExit.Click
        Me.Close()
    End Sub

    Private Sub btnSubmit_Click(sender As Object, e As EventArgs) Handles btnSubmit.Click
        Dim Name As String = txtName.Text
        Dim Gender As String = cboGender.Text
        Dim Phone As String = txtPhone.Text
        Dim Course As String = cbCourse.Text
        Dim Semester As String = txtSem.Text
        Dim Fee As String = txtFee.Text


        Dim sql As String = "INSERT INTO Students ([Name],[Gender],[Phone],[Course],[Semester],[Fee]) VALUES (@Name,@Gender,@Phone,@Course,@Semester,@Fee)"
        Try
            conn.Open()

            cmd = conn.CreateCommand
            cmd.Connection = conn

            cmd.CommandText = sql

            cmd.Parameters.AddWithValue("@Name", Name)
            cmd.Parameters.AddWithValue("@Gender", Gender)
            cmd.Parameters.AddWithValue("@Phone", Phone)
            cmd.Parameters.AddWithValue("@Course", Course)
            cmd.Parameters.AddWithValue("@Semester", Semester)
            cmd.Parameters.AddWithValue("@Fee", Fee)

            cmd.ExecuteNonQuery()

            MessageBox.Show("Data inserted successfully!")

        Catch ex As Exception
            MessageBox.Show("An error occurred: " + ex.Message)
        Finally
            ' Close the connection
            If conn IsNot Nothing AndAlso conn.State = ConnectionState.Open Then
                conn.Close()
            End If
        End Try

    End Sub

    Private Sub DataGridView1_CellContentClick(sender As Object, e As DataGridViewCellEventArgs) Handles DataGridView1.CellContentClick

    End Sub
End Class
